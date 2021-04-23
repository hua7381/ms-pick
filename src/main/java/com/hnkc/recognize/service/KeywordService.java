package com.hnkc.recognize.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.hnkc.recognize.model.vo.KeywordReg;
import com.hnkc.recognize.other.Tool;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KeywordService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<String> test1() {
        List<String> res = new ArrayList<String>();
        String content = "[报警人- 20/09/03 14:13:45]内容:[报警人- 20/09/03 14:13:45]内容:报警人称：其被人通过电话的方式骗了0元。涉案手机号码18451538889，备注：对方知晓我曾网贷，曾于6月15日银行卡到账1000元，但我当时已于6月19日还清欠款。当时的网贷平台并不是如今报称的平台，对方今天给我电话催款，要求我归还2300元，报称没有合同，诈骗恐吓，对qq号：1137151727。来自：微信小程序[佛山市公安局-赵智豪 20/09/03 14:22:54]内容:致电报警人了解:现时接到18451538889电话，对方得知其曾经贷款，让其偿还债务，但报警人早已还清欠款，怀疑对方是诈骗电话，暂无损失，需要警察指引处理。";
        String json = Tool.readFileFromClasspath("keywords.json");
        List<KeywordReg> regs = JSON.parseArray(json, KeywordReg.class);
        res = check(content, regs);
        return res;
    }

    private List<String> check(String content, List<KeywordReg> regs) {
        List<String> res = new ArrayList<String>();
        for(KeywordReg reg : regs) {
            String keyword = checkReg(content, reg);
            if(StringUtils.isNotEmpty(keyword)) {
                res.add(keyword);
            }
        }
        return res;
    }

    private String checkReg(String content, KeywordReg reg) {
        boolean fit = false;
        for(String word : reg.getContainList()) {
            if(content.contains(word)) {
                fit = true;
                break;
            }
        }
        for(String word : reg.getExcludeList()) {
            if(content.contains(word)) {
                fit = false;
                break;
            }
        }
        if(fit) {
            return reg.getKeyword();
        } else {
            return null;
        }
    }
    
}
