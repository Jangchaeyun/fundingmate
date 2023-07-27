import React from "react";
import Desc from "../Desc/Desc";
import { Link } from "react-router-dom";
import "../../../pages/Funddetail/Funddetail.css";
import Header from "../../Header/Header";
import Footer from "../../Footer/Footer";

const FundContact = () => {
  return (
    <>
      <Header />
      <div className="desc">
        <Desc />
        <div className="menu">
          <hr />
          <div className="menu_items">
            <Link className="story " to={"/fund-detail/story"}>
              스토리
            </Link>
            <div className="contact_box">
              <Link className="contact active" to={"/fund-detail/contact"}>
                문의
              </Link>
              <div className="contact_count">0</div>
            </div>
            <Link className="guide" to={"/fund-detail/guide"}>
              안내
            </Link>
          </div>
        </div>
        <div className="con">
          <p className="con_subtitle">
            여러분들이 궁금한 모든 것들을 편하게 질문해주세요!!!
          </p>
          <p className="con_title">문의 작성 시 유의사항</p>
          <li className="con_info">
            리워드 관련 문의는 댓글에 달아주시면 정확한 답변을 받을 수 있습니다.
          </li>
          <textarea className="con_input" />
          <button type="submit" className="fund_submit">
            문의하기
          </button>

          <div className="con_list">등록된 문의가 없습니다.</div>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default FundContact;
