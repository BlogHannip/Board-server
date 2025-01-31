import {configureStore, createSlice, PayloadAction} from "@reduxjs/toolkit"

interface UserState{
    email:string;
    nickName:string;
}

const initialState: UserState = {
    email: "abc123@gmail.com",
    nickName: "abc123",
};
const userSlice = createSlice({
    name:"user",
    initialState,
    reducers:{
        login: (state, action: PayloadAction<UserState>) => {
            return action.payload; // 로그인 성공 시 사용자 정보를 저장
        },
        logout: () => {
            return null; // 로그아웃 시 null 반환
        },
        updateEmail(state,action){
            state.email = action.payload;
        },
        updateNickName(state,action) {
          state.nickName =action.payload;
        },
    },
});
export const store = configureStore({
    reducer:{
        user:userSlice.reducer,
    },
});

export const{updateEmail,updateNickName,logout,login} =userSlice.actions;

export default store;