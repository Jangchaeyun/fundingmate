import React from 'react';
import Header from "../../Component/Header/Header";
import CorFooter from "../../Component/Footer/CorFooter";
import FindIdPwForm from "../../Component/Login/FindIdPw/FindIdPwForm";
import FindId from "./FindId";
import FindIdForm from "../../Component/Login/FindIdPw/FindIdForm";

function FindIdPw(props) {
    return (
        <div className="wrap">
            <Header />
            <FindIdForm />
            <CorFooter />
        </div>
    );
}

export default FindIdPw;