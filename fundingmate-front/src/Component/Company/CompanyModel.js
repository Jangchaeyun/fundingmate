import React from "react";
import "../../pages/Company/CompanyModel.css";
import {
  IeOutlined,
  InstagramOutlined,
  FacebookOutlined,
  CloseOutlined,
  TwitterOutlined,
} from "@ant-design/icons";

const CompanyModel = ({ reward, onClose }) => {
  return (
    <div className="company_model" onClick={onClose}>
      <div className="close">
        <div className="reward_manufact">{reward.manufacturer}</div>
      </div>
      <p>{reward.businessAddress}</p>
      {reward.websiteUrl && (
        <div className="company_url">
          <IeOutlined className="ieicon" />
          <a href={reward.websiteUrl} className="website_url">
            {reward.websiteUrl}
          </a>
        </div>
      )}
      {reward.instagramUrl && (
        <div className="company_url">
          <InstagramOutlined className="ieicon" />
          <a href={reward.instagramUrl} className="website_url">
            {reward.instagramUrl}
          </a>
        </div>
      )}
      {reward.facebookUrl && (
        <div className="company_url">
          <FacebookOutlined className="ieicon" />
          <a href={reward.facebookUrl} className="website_url">
            {reward.facebookUrl}
          </a>
        </div>
      )}
      {reward.twitterUrl && (
        <div className="company_url">
          <TwitterOutlined className="ieicon" />
          <a href={reward.twitterUrl} className="website_url">
            {reward.twitterUrl}
          </a>
        </div>
      )}
    </div>
  );
};

export default CompanyModel;
