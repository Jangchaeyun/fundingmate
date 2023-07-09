import React from 'react';
import CorFooter from "../../Component/Footer/CorFooter";
import JoinForm from "../../Component/Login/JoinForm";
import Header from "../../Component/Header/Header";

function Join(props) {
    return (
        <div className="wrap">
            <Header />
            <JoinForm/>
            <CorFooter />
        </div>
    );
}

export default Join;