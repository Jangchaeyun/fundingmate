import React from "react";
import "../../pages/Checkout/Checkout.css";
import { CaretRightOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";

const CheckCategory = () => {
  let navigate = useNavigate();
  return (
    <div className="rew_check_category">
      <div className="rew_check_header">실버 커팅볼 스퀘어 체인 여자 팔찌</div>
      <div className="rew_check_info">펀딩을 마치면 결제 예약 상태입니다.</div>

      <h1 className="rew_title">리워드 선택</h1>
      <div
        className="rew_category_card"
        onClick={() => {
          navigate("/reward-checkout");
        }}
      >
        <h1 className="rew_price">12,345원</h1>
        <div className="rew_sub_content">
          <div className="rew_count">무제한 | 0개 펀딩</div>
          <div className="rew_date">예상 배송일 2023.07.23</div>
        </div>
        <div className="right">
          <CaretRightOutlined />
        </div>
        <div className="rew_content">실버 커팅볼 스퀘어 체인 여자 팔찌</div>
        <div className="rew_content_info">
          실버 커팅볼 스퀘어 체인 여자 팔찌 1개 분량입니다.
        </div>
      </div>
      <div className="rew_category_card">
        <h1 className="rew_price">12,345원</h1>
        <div className="rew_sub_content">
          <div className="rew_count">무제한 | 0개 펀딩</div>
          <div className="rew_date">예상 배송일 2023.07.23</div>
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

export default CheckCategory;
