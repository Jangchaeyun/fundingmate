import React, {useState} from "react";
import "./MakeReward4.css";
import "./MakeRewardCommon.css";
import { DatePicker, Space } from 'antd';
import {PlusCircleOutlined} from "@ant-design/icons";
const { RangePicker } = DatePicker;
const MakeReward4 = () => {

    return (
        <>
        <div className="investMake-wrapper">
            <div className="proj-progress-div">
                <div className="proj-progress proj-progress-common proj-progress-line">1</div>
                <div className="proj-progress proj-progress-common proj-progress-line">2</div>
                <div className="proj-progress proj-progress-common proj-progress-line">3</div>
                <div className="proj-progress-ing proj-progress-common proj-progress-line">4</div>
                <div className="proj-progress proj-progress-common">5</div>
            </div>

            <p className="custom-font-title">
                <b>환불 및 교환 정책을 적어주세요</b>
            </p>

            <p className="custom-font-text">
               리워드 안내에 노출됩니다.
            </p>

            <br/>
            <br/>

            <p className="custom-font-sub-title">
                <b>진행자의 환불 및 교환 정책</b>
            </p>

            <textarea type="text" name="rewardRefundExchangePolicy" className="rew-textarea" placeholder="환불 및 교환 정책외에 추가사항이 있으시면 적어주세요."/>

            <br/>
            <br/>
            <br/>

            <p className="custom-font-sub-title">
                <b>문의 가능한 번호</b>
            </p>

            <input type="text" name="rewardContact" className="input-box"/>

            <br/>
            <br/>
            <br/>

            <p className="custom-font-sub-title">
                <b>문의 이메일</b>
            </p>

            <input type="text" name="rewardContact" className="input-box"/>

            <br/>
            <br/>
            <br/>

            <div className="rew-notice">
                <p><b>펀딩메이트의 환불 및 교환 정책 기본사항</b></p>
                <p style={{fontSize:"15px"}}>1. 프로젝트 기간 중에는 자유롭게 마이 페이지에서 펀딩 취소가 가능합니다.</p>
                <p style={{fontSize:"15px"}}>2. 펀딩을 받아야만 생산을 시작할 수 있는 크라우드 펀딩 특성상, 프로젝트 종료 이후에는 단순 변심으로 인한 교환이나 환불이 불가하니 이 점 양해 부탁드립니다.</p>
                <p style={{fontSize:"15px"}}>3. 리워드 배송일이 예상보다 지연되는 경우, 새소식과 후원자 분들의 이메일을 통해 안내해드리겠습니다.<br/>
                    이에 관한 문의는 이메일 "fundingmate@naver.com"로 문의바랍니다.
                </p>
                <p style={{color:"#E93232" , fontSize:"14px"}}><b>프로젝트 종료일 이후에 리워드와 관련된 환불 및 교환은 프로젝트 제작자가 약속하는 것에 따르며 펀딩메이트는 이에 책임지지 않습니다.</b></p>
            </div>

            <br/>
            <br/>
            <br/>

            <p className="custom-font-sub-title">
                <b>상품정보고시</b>
            </p>

            <p className="custom-font-text">
                리워드를 제공하는 경우, 상품정보를 반드시 입력해야 합니다.
                여러 종류의 리워드를 제공하는 경우, 다수의 상품정보를 등록해야 합니다.
            </p>



        </div>
        </>
    );
};
export default MakeReward4;
