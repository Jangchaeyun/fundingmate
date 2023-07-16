import React from "react";
import { Navigation, Pagination, Scrollbar, A11y } from "swiper";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "../../pages/Category/Category.css";

const Category = () => {
  return (
    <div className="category">
      <div className="category_cards">
        <Swiper
          modules={[Navigation, Pagination, Scrollbar, A11y]}
          spaceBetween={6}
          slidesPerView={10}
          navigation={true}
        >
          <SwiperSlide>
            <div className="category_card">
              <img src="/assets/imgs/category1.png" className="category_img" />
            </div>
            <div className="category_name"> 테크/가전</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img src="/assets/imgs/category2.png" className="category_img2" />
            </div>
            <div className="category_name2"> 패션/잡화</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img src="/assets/imgs/category3.png" className="category_img3" />
            </div>
            <div className="category_name3"> 홈/리빙</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img src="/assets/imgs/category4.png" className="category_img4" />
            </div>
            <div className="category_name4"> 뷰티</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img src="/assets/imgs/category5.png" className="category_img5" />
            </div>
            <div className="category_name4"> 푸드</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img src="/assets/imgs/category6.png" className="category_img6" />
            </div>
            <div className="category_name4"> 출판</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img src="/assets/imgs/category7.png" className="category_img7" />
            </div>
            <div className="category_name7"> 클래스/컨설팅</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img src="/assets/imgs/category8.png" className="category_img8" />
            </div>
            <div className="category_name8"> 레저/아웃도어</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img src="/assets/imgs/category9.png" className="category_img9" />
            </div>
            <div className="category_name9"> 스포츠/모빌리티</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img
                src="/assets/imgs/category10.png"
                className="category_img10"
              />
            </div>
            <div className="category_name10"> 컬처/아티스트</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img
                src="/assets/imgs/category11.png"
                className="category_img11"
              />
            </div>
            <div className="category_name11"> 캐릭터/굿즈</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img
                src="/assets/imgs/category12.png"
                className="category_img12"
              />
            </div>
            <div className="category_name12"> 반려동물</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img
                src="/assets/imgs/category13.png"
                className="category_img13"
              />
            </div>
            <div className="category_name13"> 베이비/키즈</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img
                src="/assets/imgs/category14.png"
                className="category_img14"
              />
            </div>
            <div className="category_name14"> 게임/취미</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="category_card">
              <img
                src="/assets/imgs/category15.png"
                className="category_img15"
              />
            </div>
            <div className="category_name15"> 여행/숙박</div>
          </SwiperSlide>
        </Swiper>
      </div>
    </div>
  );
};

export default Category;
