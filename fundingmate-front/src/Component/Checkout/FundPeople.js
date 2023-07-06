import React from "react";
import "../../pages/Checkout/FundPeople.css";
import { useNavigate } from "react-router-dom";

const FundPeople = () => {
  let navigate = useNavigate();
  return (
    <div>
      <div className="fund_checkout_header">
        실버 커팅볼 스퀘어 체인 여자 팔찌
      </div>
      <h1 className="new_fundpeople">투자 회원 등록</h1>
      <div className="new_fundpeople_form">
        <p className="new_fundpeople_form_title">휴대폰 본인인증</p>
        <p className="new_fundpeople_name">이름(실명)</p>
        <input
          type="text"
          placeholder="실명을 입력해주세요."
          className="new_fundpeople_name_input"
        />
        <p className="people_num">주민등록번호</p>
        <div className="people_num_inputs">
          <input
            type="text"
            placeholder="앞6자리"
            className="people_num_input"
          />
          <div className="dash">-</div>
          <input
            type="text"
            placeholder="뒤6자리"
            className="people_num_input"
          />
        </div>
        <p className="call_type">통신사 선택</p>
        <select className="call_type_input">
          <option value="0">KT</option>
          <option value="0">SKT</option>
          <option value="0">LG</option>
          <option value="0">알뜰폰</option>
        </select>
        <p className="call_number">휴대폰 번호</p>
        <div className="call_number_inputs">
          <input
            type="text"
            placeholder="‘-’ 없이 숫자만 입력해주세요."
            className="call_number_input"
          />
          <button type="submit" className="authent">
            인증요청
          </button>
        </div>
      </div>
      <button
        className="next_btn"
        onClick={() => {
          navigate("/fund-checkout/checkfund");
        }}
      >
        다음
      </button>
    </div>
  );
};

export default FundPeople;
