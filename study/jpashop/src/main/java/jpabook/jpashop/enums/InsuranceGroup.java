package jpabook.jpashop.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum InsuranceGroup {
    CASH("DB손보", Arrays.asList("대장용종", "임플란트", "치조골", "암진단")),
    CARD("한화생명", Arrays.asList("대장용종", "임플란트", "치조골", "암진단")),
    ETC("기타", Arrays.asList("정액형", "실손형")),
    EMPTY("없음", Collections.emptyList());

    private final String title;
    private final List<String> insuranceList;

    InsuranceGroup(String title, List<String> insuranceList) {
        this.title = title;
        this.insuranceList = insuranceList;
    }

    public static InsuranceGroup findByPayCode(String code){
        return Arrays.stream(InsuranceGroup.values())
                .filter(payGroup -> payGroup.hasPayCode(code))
                .findAny()
                .orElse(EMPTY);
    }

    public boolean hasPayCode(String code){
        return insuranceList
                .stream()
                .anyMatch(pay -> pay.equals(code));
    }

    public String getTitle() {
        return title;
    }
}
