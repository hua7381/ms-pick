package com.hnkc.recognize.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hnkc.recognize.dao.RegDao;
import com.hnkc.recognize.dao.WordDao;
import com.hnkc.recognize.frame.Config;
import com.hnkc.recognize.model.po.Reg;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangguihua
 * @date 2021年7月15日
 */
@Service
public class KeywordService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Config config;

    @Autowired
    RegDao regDao;

    @Autowired
    WordDao wordDao;

    private static List<Reg> regs = null;
    private static List<String> words = null;
    private static Long refreshTime = null;
    private static final Long CACHE_TIME = 1000L * 60 * 5;

    public List<String> pickList(String content) {

        if(refreshTime == null || System.currentTimeMillis() - refreshTime > CACHE_TIME) {
            regs = regDao.selectList(Wrappers.lambdaQuery(Reg.class));
            words = wordDao.getWords(config.getWordSql());
            refreshTime = System.currentTimeMillis();
        }

        List<String> list = new ArrayList<String>();
        Set<String> set = new HashSet<String>();
        List<String> result = new ArrayList<String>();

        list.addAll(checkByRegs(content, regs));
        list.addAll(checkByWords(content, words));

        for(String one : list) {
            if (!set.contains(one)) {
                set.add(one);
                result.add(one);
            }
        }

        return result;
    }

    private List<String> checkByWords(String content, List<String> words) {
        List<String> list = new ArrayList<String>();
        for(String one : words) {
            if(content.contains(one)) {
                list.add(one);
            }
        }
        return list;
    }

    private List<String> checkByRegs(String content, List<Reg> regs) {
        List<String> list = new ArrayList<String>();
        for (Reg reg : regs) {
            String keyword = checkReg(content, reg);
            if (StringUtils.isNotEmpty(keyword)) {
                list.add(keyword);
            }
        }
        return list;
    }

    private String checkReg(String content, Reg reg) {

        boolean fit = false;

        if (content.contains(reg.getKeyword())) {
            fit = true;
        } else {
            for (String word : toList(reg.getContains())) {
                if (contain(content, word)) {
                    fit = true;
                    break;
                }
            }
        }

        for (String word : toList(reg.getExcludes())) {
            if (contain(content, word)) {
                fit = false;
                break;
            }
        }

        if (fit) {
            return reg.getKeyword();
        } else {
            return null;
        }
    }

    private List<String> toList(String words) {
        List<String> list = new ArrayList<String>();
        if (words != null) {
            for (String s : words.replaceAll(" ", "").replaceAll("，", ",").split(",")) {
                list.add(s);
            }
        }
        return list;
    }

    private boolean contain(String content, String word) {
        if (StringUtils.isEmpty(word)) {
            return false;
        }
        if (word.contains("@")) {
            for (String subWord : word.split("@")) {
                if (!content.contains(subWord)) {
                    return false;
                }
            }
            return true;
        } else {
            return content.contains(word);
        }
    }

}
