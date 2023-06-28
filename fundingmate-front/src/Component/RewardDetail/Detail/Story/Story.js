import React from "react";
import "../../../../pages/Rewarddetail/Rewarddetail.css";
import { Link } from "react-router-dom";
import Desc from "../../Desc/Desc";

const Story = () => {
  return (
    <div>
      <Desc />
      <div className="menu">
        <hr />
        <div className="menu_items">
          <div className="story">스토리</div>
          <Link className="contact" to="/register-item/contact">
            문의<sup className="contact_count">0</sup>
          </Link>
          <div className="guide">안내</div>
        </div>
      </div>
      <div className="story_content">
        안녕하세요!! 저희는 스마트보이입니다~~
        <br />
        저희는 피어싱 및 악세사리를 팔고 있습니다.
        <div className="product_img">
          <img src="/assets/imgs/product_img.jpg" className="images" />
        </div>
      </div>
    </div>
  );
};

export default Story;
