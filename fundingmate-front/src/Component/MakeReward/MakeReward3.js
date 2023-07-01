import React, {useRef} from "react";
import "./MakeReward3.css";
import "./MakeRewardCommon.css";
import {PlusOutlined} from "@ant-design/icons";
import { Button, Modal } from 'antd';
import { useState } from 'react';
import { DatePicker, Space } from 'antd';
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

    const [limitClicked, setLimitClicked] = useState(null);
    const handleLimitButtonClick = (buttonId) =>{
        setLimitClicked(buttonId);
    }
    const [textareaContent, setTextareaContent] = useState("");
    const textareaMaxLength = 400;
    const handleMaxLengthChange = (event) => {
        const {value} = event.target;
        if(value.length <= textareaMaxLength){
            setTextareaContent(value);
        }
    };
    const [inputContent, setInputContent] = useState("");
    const inputMaxLength = 60;
    const handleInputChange = (event) => {
        const {value} = event.target;
        if(value.length <= inputMaxLength){
            setInputContent(value);
        }
    };

    const onChange = (date, dateString) => {
        console.log(date, dateString);
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

            <Button type="primary"  className="rew-card-add-button" id="rew-card-add-button"
                    icon={<PlusOutlined id="rew-card-add-icon" style={{fontSize:"11px", marginRight:"1px"}}/>}
                    onClick={() => setModalOpen(true)}>
                리워드 추가
            </Button>
            <Modal
                title={<p style={{ fontSize: "25px" }}>리워드 추가</p>}
                centered
                visible={modalOpen}
                onOk={handleOk}
                onCancel={handleCancel}
                width={655}
                okText="추가" // 'OK' 버튼의 이름 변경
                cancelText="취소"
                okButtonProps={{ style: { backgroundColor: "var(--main-color)" } }}

            >
                <p className="custom-font-modal-sub-title">
                    리워드 금액
                </p>
                <input type="text" name="rewardAmount" className="input-box" placeholder="0"/>&nbsp;원

                <p className="custom-font-modal-sub-title">
                    리워드 제공 가능 수
                </p>
                <div style={{display:'flex', alignItems:'center'}}>
                <div className="rew-limit-buttons">
                    <button className={`rew-unlimited ${limitClicked === 'rew-unlimited-button' ? 'clicked' : ''}`}
                            id="rew-unlimited-button"
                            onClick={()=>handleLimitButtonClick("rew-unlimited-button")}
                    >무제한</button>
                    <button className={`rew-limit ${limitClicked==='rew-limit-button' ? 'clicked' : ''}`}
                            id="rew-limit-button"
                            onClick={()=>handleLimitButtonClick("rew-limit-button")}
                            >제한</button>

                </div>
                <input type="text" name="rewardAvailableCount" className="modal-input-box"  placeholder="0"/>&nbsp;개
                </div>

                <p className="custom-font-modal-sub-title">
                    리워드 제목
                </p>
                <div>
                <input type="text" name="rewardTitle" className="input-box" value={inputContent} onChange={handleInputChange}/>
                <div style={{fontSize:"13px", color:"#939393"}}>{inputMaxLength-inputContent.length}자 남음</div>
                </div>
                <p className="custom-font-modal-sub-title">
                    리워드 내용
                </p>
                <div>
                <textarea type="text" name="rewardContent" className="rew-textarea" value={textareaContent} onChange={handleMaxLengthChange}/>
                <div style={{fontSize:"13px", color:"#939393"}}>{textareaMaxLength-textareaContent.length}자 남음</div>
                </div>


                <p className="custom-font-modal-sub-title">
                    예상 배송일
                </p>

                <DatePicker onChange={onChange} />

                <p className="custom-font-modal-sub-title">
                    리워드 옵션
                </p>

                <div style={{display:"flex"}}>
                <p className="custom-font-modal-option-text">
                    옵션명
                </p>
                <p className="custom-font-modal-option-text" style={{marginLeft:"15%"}}>
                    옵션 값
                </p>
                </div>

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
