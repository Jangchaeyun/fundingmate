import React from "react";
import Desc from "../Desc/Desc";
import { Link } from "react-router-dom";

const FundStory = () => {
  return (
    <div>
      <Desc />
      <div className="menu">
        <hr />
        <div className="menu_items">
          <Link className="fund_story active" to={"/fund-detail/story"}>
            스토리
          </Link>
          <div className="contact_box">
            <Link className="fund_contact" to={"/fund-detail/contact"}>
              문의
            </Link>
            <div className="contact_count">0</div>
          </div>
          <Link className="fund_guide" to={"/fund-detail/guide"}>
            안내
          </Link>
        </div>
      </div>
      <div className="fund_story_content">창업 아이템 소개</div>
      <div className="fund_story_content_text"></div>
      <div className="fund_story_content">창업 아이템의 사업성</div>
      <div className="fund_story_content_text"></div>
      <div className="fund_story_content">창업 아이템 의 가치</div>
      <div className="fund_story_content_text"></div>
      <div className="fund_story_content">창업 아이템 의 기대효과</div>
      <div className="fund_story_content_text"></div>
    </div>
  );
};

export default FundStory;
