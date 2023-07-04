import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { CaretRightOutlined } from "@ant-design/icons";

const CheckoutFundCartegory = () => {
  const navigate = useNavigate();
  const [number, setNumber] = useState(1);
  const [price, setPrice] = useState(12345);

  const count = (type) => {
    if (type === "plus") {
      const updatedNumber = number + 1;
      setNumber(updatedNumber);
      setPrice((prevPrice) => prevPrice + 12345);
    } else if (type === "minus") {
      if (number <= 1) {
        return;
      }
      const updatedNumber = number - 1;
      setNumber(updatedNumber);
      setPrice((prevPrice) => prevPrice - 12345);
    }
  };

  return (
    <div>
      <div className="fund_box">
        <h1 className="fund_box_price">{price.toLocaleString()}원</h1>
        <div className="right">
          <CaretRightOutlined
            onClick={() => {
              navigate("/fund-checkout/checkout");
            }}
          />
        </div>
        <div className="fund_count_minus_plus">
          <button className="minus" onClick={() => count("minus")}>
            -
          </button>
          <div className="fund_minus_plus_count">
            <div className="fund_minus_plus_count_number">{number}</div>
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
