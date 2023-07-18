import {configureStore} from '@reduxjs/toolkit';
import {persistReducer} from 'redux-persist'; 
import storage from "redux-persist/lib/storage/session";  //localStorage에 저장

//import storageSession from "redux-persist/lib/storage/session";  //storageSession에 저장
//redux의 store는 페이지를 새로고침 할 경우 state가 날라감
//localStorage or storageSession에 저장하기 위해 redux-persist 사용
//npm install --save redux-persist
//npm install --save @reduxjs/toolkit

const reducer = (currentState, action) => {
    if(currentState==undefined) {
        return({
            Authorization:'',
            UserId:''
        })
    }
    const newState = {...currentState};
    switch(action.type) {
        case "NEWTOKEN": newState.Authorization=action.payload; break;
        case "USERID": newState.UserId=action.payload; break;
        default:
    }
    return newState;
}

const persistConfig = {
    key:'root',
    storage
}

const persistedReducer = persistReducer(persistConfig, reducer);
const store = configureStore({
    reducer:persistedReducer,
    middleware:(getDefaultMiddleware)=> 
        getDefaultMiddleware({
            serializableCheck:false,
        })
})

export default store;