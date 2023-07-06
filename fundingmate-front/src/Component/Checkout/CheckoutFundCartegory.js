import React from "react";
import { useNavigate } from "react-router-dom";
import { CaretRightOutlined } from "@ant-design/icons";

const CheckoutFundCartegory = () => {
  let navigate = useNavigate();
  function count(type) {
    const resultElement = document.getElementById("result");
    let number = resultElement.innerText;

    if (type === "plus") {
      number = parseInt(number) + 1;
    } else if (type === "minus") {
      if (number <= 0) {
        return; // Return early if number is already 1
      }
      number = number - 1;
    }

    resultElement.innerText = number;
  }
  return (
    <div>
      <div
        className="fund_box"
        onSubmit={() => {
          navigate("/reward-checkout/complete");
        }}
      >
        <h1 className="fund_box_price" id="price">
          12,345원
        </h1>
        <div className="right">
          <CaretRightOutlined />
        </div>
        <div className="fund_count_minus_plus">
          <button className="minus" onClick={() => count("minus")}>
            -
          </button>
          <div className="fund_minus_plus_count">
            <div className="fund_minus_plus_count_number" id="result">
              1
            </div>
          </div>
          <button className="plus" onClick={() => count("plus")}>
            +
          </button>
        </div>
      </div>
    </div>
  );
};

export default CheckoutFundCartegory;
