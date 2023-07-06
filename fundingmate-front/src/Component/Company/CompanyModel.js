import React from "react";
import "../../pages/Company/CompanyModel.css";
import {
  IeOutlined,
  InstagramOutlined,
  FacebookOutlined,
  CloseOutlined,
} from "@ant-design/icons";

const CompanyModel = ({ onClose }) => {
  return (
    <div className="company_model">
      <div className="close">
        <h2>스마트보이</h2>
        <CloseOutlined className="close_btn" onClick={onClose} />
      </div>
      <p>광주광역시 남구 서문대로824번길 3 (주월동) SH빌딩 4층</p>
      <div className="company_url">
        <IeOutlined className="ieicon" />
        <a href="https://smart-boy.co.kr/" className="website_url">
          https://smart-boy.co.kr/
        </a>
      </div>
      <div className="company_url">
        <InstagramOutlined className="ieicon" />
        <a
          href="https://www.instagram.com/smartboy_insta/"
          className="website_url"
        >
          https://www.instagram.com/smartboy_insta/
        </a>
      </div>
      <div className="company_url">
        <FacebookOutlined className="ieicon" />
        <a href="https://www.facebook.com/smartboy1004" className="website_url">
          https://www.facebook.com/smartboy1004
        </a>
      </div>
    </div>
  );
};

export default CompanyModel;
