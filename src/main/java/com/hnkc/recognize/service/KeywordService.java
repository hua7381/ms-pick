package com.hnkc.recognize.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hnkc.recognize.dao.RegDao;
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
    RegDao regDao;

    public List<String> pickList(String content) {
        List<Reg> regs = regDao.selectList(Wrappers.lambdaQuery(Reg.class));
        return check(content, regs);
    }

    private List<String> check(String content, List<Reg> regs) {
        List<String> list = new ArrayList<String>();
        Set<String> set = new HashSet<String>();
        for (Reg reg : regs) {
            String keyword = checkReg(content, reg);
            if (StringUtils.isNotEmpty(keyword)) {
                if (!set.contains(keyword)) {
                    set.add(keyword);
                    list.add(keyword);
                }
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
