package com.hnkc.recognize.frame;

/**
 * 枚举字典
 * @author zhangguihua
 * @date 2020/09/29
 */
public class Enums {

    public static final String DICT_TYPE_ENUM_DESCR = "BOOK_STATUS: 书籍状态, BOOK_TYPE: 书籍类别, DEPT: 部门";
    /**
     * 字典类别
     * @author zhangguihua
     * @date 2020/09/29
     */
    public enum DictTypeEnum {
        /** */
        BOOK_STATUS("BOOK_STATUS", "书籍状态"), 
        BOOK_TYPE("BOOK_TYPE", "书籍类别"), 
        DEPT("DEPT", "部门");
        private DictTypeEnum(String id, String label) {
            this.id = id;
            this.label = label;
        }
        private String id;
        private String label;
        public String getId() {
            return id;
        }
        public String getLabel() {
            return label;
        }
    }

    public static final String BOOK_STATUS_ENUM_DESCR = "TO_BORROW: 可借出, BORROWED: 已借出, TO_RETURN: 待还";
    /**
     * 书籍状态
     * @author zhangguihua
     * @date 2020/09/29
     */
    public enum BookStatusEnum {
        /** */
        TO_BORROW("TO_BORROW", "可借出"), 
        BORROWED("BORROWED", "已借出"), 
        TO_RETURN("TO_RETURN", "待还");

        private BookStatusEnum(String id, String label) {
            this.id = id;
            this.label = label;
        }

        private String id;
        private String label;
        public String getId() {
            return id;
        }
        public String getLabel() {
            return label;
        }
    }

    public static final String ELEMENT_TYPE_DESCR = "MOBILE 手机号, ID_CARD 身份证号, BANK_CARD 银行卡号, PLATE 车牌号, NAME 姓名, ADDRESS 地址, QQ QQ号, WEIXIN 微信, ALIPAY 支付宝, CASE_NO 案件编号";
    /**
     * @author zhangguihua
     * @date 2021年1月23日
     */
    public enum ElementType {
        /** */
        MOBILE("MOBILE", "手机号"),
        ID_CARD("ID_CARD", "身份证号"),
        BANK_CARD("BANK_CARD", "银行卡号"),
        PLATE("PLATE", "车牌号"),
        NAME("NAME", "姓名"),
        ADDRESS("ADDRESS", "地址"),
        QQ("QQ", "QQ号"),
        WEIXIN("WEIXIN", "微信"),
        ALIPAY("ALIPAY", "支付宝"),
        CASE_NO("CASE_NO", "案件编号");

        private ElementType(String id, String label) {
            this.id = id;
            this.label = label;
        }

        private String id;
        private String label;
        public String getId() {
            return id;
        }
        public String getLabel() {
            return label;
        }
    }

}