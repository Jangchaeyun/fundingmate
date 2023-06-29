import React from 'react';
import "./Main.css"

function Main() {
    return (
        <div className="content">
            <div className="content-title">리워드 프로젝트</div>
            <div className="content-item-list">
                <img src="" alt="" className="content-item-img"/>
                <div className="content-item">
                    <div>
                        <span>000%</span>
                        <span>0.000원</span>
                        <span>D-00</span>
                    </div>
                    <div>프로젝트 명</div>
                    <div>기업 명</div>
                </div>
            </div>
            <div className="content-title">창업 프로젝트</div>
            <div className="content-item-list">
                <img src="" alt="" className="content-item-img"/>
                <div className="content-item">
                    <div>
                        <span>000%</span>
                        <span>0.000원</span>
                        <span>D-00</span>
                    </div>
                    <div>프로젝트 명</div>
                    <div>기업 명</div>
                </div>
            </div>
            <div className="content-title">오픈예정</div>
            <div className="content-item-list">
                <img src="" alt="" className="content-item-img"/>
                <div className="content-item">
                    <div>
                        <span>000%</span>
                        <span>0.000원</span>
                        <span>D-00</span>
                    </div>
                    <div>프로젝트 명</div>
                    <div>기업 명</div>
                </div>
            </div>
        </div>
    );
}

export default Main;