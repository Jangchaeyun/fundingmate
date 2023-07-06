import { CreditCardOutlined, ReconciliationOutlined } from "@ant-design/icons";
import React from "react";
import { useNavigate } from "react-router";

const CheckoutComplete = () => {
  let navigate = useNavigate();
  return (
    <div className="checkout">
      <div className="checkout_header">실버 커팅볼 스퀘어 체인 여자 팔찌</div>
      <div className="checkout_processes">
        <div className="checkout_process">
          <CreditCardOutlined className="card" />
        </div>
        <div className="checkout_line"></div>
        <div className="checkout_process active">
          <ReconciliationOutlined className="card" />
        </div>
      </div>
      <div className="checkout_process_texts">
        <div className="checkout_process_text">카드/배송정보</div>
        <div></div>
        <div className="checkout_process_text active">결제 예약 확인</div>
      </div>
      <div className="reward_ckeckout_com">
        결제가 정상적으로 예약되었습니다.
      </div>
      <div className="button_com">
        <button
          type="button"
          className="home_btn"
          onClick={() => {
            navigate("/");
          }}
        >
          홈으로
        </button>
      </div>
    </div>
  );
};

export default CheckoutComplete;
