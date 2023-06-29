import React from "react";
import {
  CreditCardOutlined,
  ReconciliationOutlined,
  CaretRightOutlined,
} from "@ant-design/icons";

const Checkout = () => {
  return (
    <div>
      <div className="checkout_header">실버 커팅볼 스퀘어 체인 여자 팔찌</div>
      <div className="checkout_processes">
        <div className="checkout_process active">
          <CreditCardOutlined className="card" />
        </div>
        <div className="checkout_line"></div>
        <div className="checkout_process">
          <ReconciliationOutlined className="card" />
        </div>
      </div>
      <div className="checkout_process_texts">
        <div className="checkout_process_text active">카드/배송정보</div>
        <div></div>
        <div className="checkout_process_text">결제 예약 확인</div>
      </div>
      <div className="reward_list_titles">
        <div className="reward_list_title">내가 선택한 리워드</div>
        <div className="reward_add">리워드 추가</div>
      </div>
      <div className="rew_category_card">
        <h1 className="rew_price">12,345원</h1>
        <div className="rew_sub_contents">
          <div className="rew_check_count">무제한 | 0개 펀딩</div>
          <div className="rew_check_date">예상 배송일 2023.07.23</div>
        </div>
        <div className="right">
          <CaretRightOutlined />
        </div>
        <div className="rew_content">실버 커팅볼 스퀘어 체인 여자 팔찌</div>
        <div className="rew_content_info">
          실버 커팅볼 스퀘어 체인 여자 팔찌 1개 분량입니다.
        </div>
      </div>
    </div>
  );
};

export default Checkout;
