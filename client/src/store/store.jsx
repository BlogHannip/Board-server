import { configureStore, createSlice } from "@reduxjs/toolkit";

const initialState = null;

const userSlice = createSlice({
    name: "user",
    initialState,
    reducers: {
        login: (state, action) => {
            // 로그인 시 상태를 action.payload 변경
            console.log(action.payload);
            return action.payload;
        },
        logout: () => {
            // 로그아웃 시 상태를 null로 설정
            return null;
        },
        updateEmail(state, action) {
            // 상태가 null이 아닐 때만 업데이트
            if (state) {
                state.email = action.payload;
            }
        },
        updateNickName(state, action) {
            // 상태가 null이 아닐 때만 업데이트
            if (state) {
                state.nickName = action.payload;
            }
        },
    },
});

export const store = configureStore({
    reducer: {
        user: userSlice.reducer,
    },
});

export const { updateEmail, updateNickName, logout, login } = userSlice.actions;

export default store;
