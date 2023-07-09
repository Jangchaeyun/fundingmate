import React from "react";
import "../../pages/Company/CompanyModel.css";
import {
  IeOutlined,
  InstagramOutlined,
  FacebookOutlined,
  CloseOutlined,
} from "@ant-design/icons";

const CompanyModel = ({ reward, onClose }) => {
  return (
    <div className="company_model">
      <div className="close">
        <h2>{reward.manufacturer}</h2>
        <CloseOutlined className="close_btn" onClick={onClose} />
      </div>
      <p>{reward.businessAddress}</p>
      <div className="company_url">
        <IeOutlined className="ieicon" />
        <a href={reward.websiteUrl} className="website_url">
          {reward.websiteUrl}
        </a>
      </div>
      <div className="company_url">
        <InstagramOutlined className="ieicon" />
        <a href={reward.instagramUrl} className="website_url">
          {reward.instagramUrl}
        </a>
      </div>
    </div>
  );
};

export default CompanyModel;
