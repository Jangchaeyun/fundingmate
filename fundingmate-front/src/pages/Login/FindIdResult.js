import React from 'react';
import Header from "../../Component/Header/Header";
import FindIdPwForm from "../../Component/Login/FindIdPw/FindIdPwForm";
import CorFooter from "../../Component/Footer/CorFooter";
import FindIdResultForm from "../../Component/Login/FindIdPw/FindIdResultForm";

function FindIdResult(props) {
    return (
        <div className="wrap">
            <Header />
            <FindIdResultForm />
            <CorFooter />
        </div>
    );
}

export default FindIdResult;