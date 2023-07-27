import React from "react";
import Desc from "../Desc/Desc";
import { Link } from "react-router-dom";
import Header from "../../Header/Header";
import Footer from "../../Footer/Footer";

const FundGuide = () => {
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
              <Link className="contact" to={"/fund-detail/contact"}>
                문의
              </Link>
              <div className="contact_count">0</div>
            </div>
            <Link className="guide active" to={"/fund-detail/guide"}>
              안내
            </Link>
          </div>
        </div>
        <div className="guide_infos">
          <div className="guide_title">진행자 교환 및 환불 정책</div>
          <p className="guide_content">
            다음과 같은 사항 시 교환 및 환불이 안됩니다.
            <br />
            -참여자의 책임 있는 사유로 리워드가 멸실/훼손된 경우(단지 확인을
            위한 포장 훼손 제외) -참여자의 사용/소비에 의해 리워드의 가치가
            감소한 경우
            <br />
            -시간 경과로 인해 재판매가 곤란할 정도로 리워드 가치가 상실한 경우
            <br />
            -참여자의 단순 변심 -진행자를 통환 교환/환불 접수 절차 없이 임의로
            반송한 경우
            <br />
            -택배 배송중 파손된 건에 대해서는 교환 및 환불 가능합니다.
            <br />
            문의처 : 0437425275
          </p>
          <div className="guide_title">메이트 교환 및 환불 정책</div>
          <p className="guide_content">
            - 펀딩 취소는 프로젝트 종료 전까지만 마이페이지의 펀딩한
            프로젝트에서 할 수 있으며, 크라우드펀딩의 특성상 크라우디에서는
            프로젝트 종료 이후 단순 변심에 의한 펀딩 취소가 불가능합니다.
            <br />- 프로젝트 종료 이후의 AS, 교환 및 환불에 관한 문의는 진행자의
            교환 및 환불 정책을 따르거나 진행자의 연락처로 문의해야 합니다.
          </p>
          <div className="guide_title">유의사항</div>
          <p className="guide_content">
            - 주소 변경은 프로젝트 종료 전까지만 마이페이지의 펀딩한
            프로젝트에서 할 수 있습니다.
            <br />- 결제수단 변경은 결제 전까지만 가능하며 결제완료 이후에는
            불가능합니다.
            <br />- 리워드 옵션 변경은 불가능하며, 기존 펀딩 내역을 취소하신 후
            다시 펀딩하셔야 합니다.
          </p>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default FundGuide;
