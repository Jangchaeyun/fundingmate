import React from 'react';
import "./Main.css"
import RewordContent from "../Content/RewordContent";
import InvestmentContent from "../Content/InvestmentContent";
import ComingsoonContent from "../Content/ComingsoonContent";

function Main() {
    return (
        <div className="container">
            <RewordContent />
            <InvestmentContent />
            <ComingsoonContent />
        </div>
    );
}

export default Main;