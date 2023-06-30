import React, {useRef} from "react";
import "./MakeReward3.css";
import "./MakeRewardCommon.css";
import {PlusOutlined} from "@ant-design/icons";
import { Button, Modal } from 'antd';
import { useState } from 'react';
const MakeReward3 = () => {
    const [modalOpen, setModalOpen] = useState(false);
    const [cards, setCards] = useState([]); // 카드 배열 추가

    const handleOk = () => {
        setModalOpen(false);
        setCards(prevCards => [...prevCards, {}]); // 새로운 카드 객체를 배열에 추가
    };

    const handleCancel = () => {
        setModalOpen(false);
    };

    return (
        <>
        <div className="investMake-wrapper">

            <div className="proj-progress-div">
                <div className="proj-progress proj-progress-common proj-progress-line">1</div>
                <div className="proj-progress proj-progress-common proj-progress-line">2</div>
                <div className="proj-progress-ing proj-progress-common proj-progress-line">3</div>
                <div className="proj-progress proj-progress-common proj-progress-line">4</div>
                <div className="proj-progress proj-progress-common">5</div>
            </div>


            <p className="custom-font-sub-title">
                <b>프로젝트 리워드를 구성해주세요</b>
            </p>

            <p className="custom-font-text">
                프로젝트 시작을 위해서는 <b style={{color:"#E93232"}}>최소 1개 이상의 리워드</b>가 있어야 합니다.<br/>
                배송이 필요한 리워드는 배송비가 포함된 가격을 적어주세요.
            </p>


          {/*  <button className="rew-card-add-button">
                <PlusOutlined className="rew-card-add-icon" style={{fontSize:"11px", marginRight:"1px"}}/>리워드 추가
            </button>*/}

            <Button type="primary"  className="rew-card-add-button" onClick={() => setModalOpen(true)}>
                <PlusOutlined className="rew-card-add-icon" style={{fontSize:"11px", marginRight:"1px"}}/>리워드 추가
            </Button>
            <Modal
                title="Modal 1000px width"
                centered
                visible={modalOpen}
                onOk={handleOk}
                onCancel={handleCancel}
                width={1000}
            >
                <p>some contents...</p>
                <p>some contents...</p>
                <p>some contents...</p>
            </Modal>
            {cards.map((card, index) => (
            <div className="make-rew-card">
                <div className="make-rew-card-div">
                <div>
                <div className="make-rew-card-price">12,345원</div>
                <div className="make-rew-card-contents">
                    <div className="make-rew-card-count">무제한&nbsp;|&nbsp;0개 펀딩</div>
                    <div className="make-rew-card-date">예상 배송일 2023.07.23</div>
                </div>

                <div className="make-rew-card-sub-content">실버 커팅볼 스퀘어 체인 여자 팔찌</div>
                <div className="make-rew-card-sub-content-info">
                    실버 커팅볼 스퀘어 체인 여자 팔찌 1개 분량입니다.
                </div>
                </div>
                <div>
                <div className="make-rew-card-edit-button-div">
                    <button className="make-rew-card-edit-button">복사</button>
                    <button  className="make-rew-card-edit-button">수정</button>
                    <button  className="make-rew-card-edit-button make-rew-card-edit-button-delete">삭제</button>
                </div>
                </div>
                </div>
            </div>
            ))}

            <div className="button-top-margin"></div>
            <div className="investMake-button-div">
            <button className="investMake-prev-button"> 이전 단계 </button>
            <button className="investMake-next-button"> 다음 단계 </button>
            </div>
                <div className="button-botoom-margin"></div>

        </div>
        </>
    );
};
export default MakeReward3;
