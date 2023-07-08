import React from 'react';
import Header from "../../Component/Header/Header";
import CorFooter from "../../Component/Footer/CorFooter";
import FindIdPwForm from "../../Component/Login/FindIdPw/FindIdPwForm";

function FindIdPw(props) {
    return (
        <div className="wrap">
            <Header />
            <FindIdPwForm />
            <CorFooter />
        </div>
    );
}

export default FindIdPw;