import "./InvestmentContent.css"
import {Swiper, SwiperSlide} from "swiper/react";
import {A11y, Navigation, Scrollbar} from "swiper";

function RewordContent() {
    return (
        <section className="content">
            <div className="content-title">창업 프로젝트</div>
            <div className="content-proj">
                <Swiper
                    style={{
                        "--swiper-navigation-size": "20px",

                    }}
                    modules={[Navigation, Scrollbar, A11y]}
                    spaceBetween={20}
                    slidesPerView={4}
                    navigation
                    loop={true}
                >
                    <SwiperSlide className="content-slide">
                        <a href="#!" className="content-item-list-link">
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
                        </a>
                    </SwiperSlide>
                    <SwiperSlide className="content-slide">
                        <a href="#!" className="content-item-list-link">
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
                        </a>
                    </SwiperSlide>
                    <SwiperSlide className="content-slide">
                        <a href="#!" className="content-item-list-link">
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
                        </a>
                    </SwiperSlide>
                    <SwiperSlide className="content-slide">
                        <a href="#!" className="content-item-list-link">
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
                        </a>
                    </SwiperSlide>
                    <SwiperSlide className="content-slide">
                        <a href="#!" className="content-item-list-link">
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
                        </a>
                    </SwiperSlide>
                    <SwiperSlide className="content-slide">
                        <a href="#!" className="content-item-list-link">
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
                        </a>
                    </SwiperSlide>
                </Swiper>
            </div>
        </section>
    );
}

export default RewordContent;