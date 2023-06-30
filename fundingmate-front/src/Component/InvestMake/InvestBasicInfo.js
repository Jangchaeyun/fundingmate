import React from "react";
import "./InvestBasicInfo.css";
import "./InvestMakeCommon.css";
import { DatePicker, Space } from 'antd';
import {PlusCircleOutlined} from "@ant-design/icons";
const { RangePicker } = DatePicker;
const InvestBasicInfo = () => {

    return (
        <>
        <div className="investMake-wrapper">
            <div className="proj-progress-div">
                <div className="proj-progress-ing proj-progress-common proj-progress-line">1</div>
                <div className="proj-progress proj-progress-common proj-progress-line">2</div>
                <div className="proj-progress proj-progress-common proj-progress-line">3</div>
                <div className="proj-progress proj-progress-common proj-progress-line">4</div>
                <div className="proj-progress proj-progress-common">5</div>
            </div>

            <p className="custom-font-title">
                <b>프로젝트의 성공 조건 & 수수료 안내</b>
            </p>

            <p className="custom-font-text">
                프로젝트 종료일 기준 모금액이 목표금액의 100% 이상인 경우에만 프로젝트가 성공하게 됩니다.<br/>
                프로젝트가 성공한 경우, 크라우디는 모금액에서 수수료(12%, vat 별도)를 제한 금액을 정산해드립니다.
            </p>

            <br/>
            <br/>

            <p className="custom-font-sub-title">
                <b>프로젝트의 제목을 적어주세요</b>
            </p>

            <p className="custom-font-text">
                프로젝트의 핵심 내용을 담을 수 있는 간결한 제목을 정해주세요.
            </p>
            <input type="text" name="pri_name" className="input-box"/>

            <br/>
            <br/>
            <br/>

            <p className="custom-font-sub-title">
                <b>목표 금액을 적어주세요</b>
            </p>

            <p className="custom-font-text">
                최소 100,000원 이상이어야 합니다.
            </p>
            <input type="text" name="pri_target_amount" className="input-box" placeholder="0"/> &nbsp;원

            <br/>
            <br/>
            <br/>

            <p className="custom-font-sub-title">
                <b>프로젝트의 진행 기간을 적어주세요</b>
            </p>

            <Space direction="vertical" size={12}>
                <RangePicker />
            </Space>

            <br/>
            <br/>
            <br/>

            <p className="custom-font-sub-title">
                <b>프로젝트의 대표 이미지를 등록해주세요</b>
            </p>
            <p className="custom-font-text">
                홈페이지와 외부 공유를 했을 때 보여집니다.<br/>
                프로젝트를 한 눈에 나타낼 수 있는 이미지를 등록해주세요.
            </p>
            <br/>

            <div className="ibi-image-upload">
                <div className="ibi-image-upload-info">
                <div style={{marginBottom: "2%"}}><PlusCircleOutlined style={{ fontSize: "25px", cursor:"pointer"}}/></div>
                최적 사이즈 740*492px
                </div>
                </div>
            <br/>
            <br/>
            <br/>
            <p className="custom-font-sub-title">
                <b>프로젝트 키워드를 적어주세요</b>
            </p>
            <p className="custom-font-text">
                (선택사항) 제목 외에도 키워드 검색 시 검색 결과에 프로젝트가 나타납니다.
            </p>
            <input type="text" name="pri_keyword" className="input-box" placeholder="키워드, 키워드, 키워드, 키워드, 키워드"/>
            <div className="button-top-margin"></div>
            <div className="investMake-button-div">
            <div className="investMake-next-button"> 다음 단계 </div>
            </div>
            <div className="button-botoom-margin"></div>
        </div>
        </>
    );
};
export default InvestBasicInfo;
