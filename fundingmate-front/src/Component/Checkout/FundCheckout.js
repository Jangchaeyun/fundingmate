import React from "react";
import FundCategory from "./FundCategory";
import { useNavigate } from "react-router-dom";
import { CreditCardOutlined, ReconciliationOutlined } from "@ant-design/icons";

const FundCheckout = () => {
  let navigate = useNavigate();
  return (
    <div className="funchekout_box">
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

      <FundCategory />
      <form className="checkout_form">
        <p className="title">결제자 핸드폰 번호</p>
        <div className="phone">
          <input type="text" className="phone_num" required />
          <button type="submit" className="num_auth">
            인증
          </button>
        </div>
        <p className="title">결제정보 입력</p>
        <div className="card_number">
          <input type="text" className="card_num" required />
          <input type="text" className="card_num" required />
          <input type="password" className="card_num" required />
          <input type="password" className="card_num" required />
        </div>
        <p className="title">카드 비밀번호</p>
        <input type="password" className="card_pw" placeholder="앞 2자리" />
        <p className="title">유효기간</p>
        <div className="card_date">
          <input
            type="text"
            className="card_date_yy_mm"
            placeholder="MM"
            required
          />
          <input
            type="text"
            className="card_date_yy_mm"
            placeholder="YY"
            required
          />
        </div>
        <p className="title">생년월일</p>
        <input
          type="text"
          className="birthday"
          placeholder="법인 카드의 경우 사업자 등록번호 10자리 숫자"
          required
        />

        <p className="title">할부기간</p>
        <select className="period">
          <option value="0">일시불</option>
          <option value="1">할부</option>
        </select>

        <p className="sub_title">정보 동의</p>
        <div className="check_box">
          <label className="check_title">
            <input type="checkbox" className="check" required /> [필수] 결제에
            필요한 정보 제공에 동의합니다.
          </label>
          <br />
          <label className="check_title">
            <input type="checkbox" className="check" required /> [필수] 리워드
            제공에 필요한 정보 제공에 동의합니다.
          </label>
        </div>
        <button
          type="submit"
          className="complete"
          onSubmit={() => {
            navigate("/fund-checkout/complete");
          }}
        >
          결제 예약 완료
        </button>
      </form>
    </div>
  );
};

export default FundCheckout;
