import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import apiClient from "../apiClient.tsx";

interface LoginResponse {
    accessToken: string | null;
    refreshToken: string | null;
    message: string;
    email: string | null;
}

interface AuthState {
    isAuthenticated: boolean;
    email: string | null;
}

const initialState: AuthState = { // 타입스크립트 특성상 선언에 엄격해야함
    isAuthenticated: false,
    email: null,
};

// ✅ 로그인 상태 체크 (비동기)
export const checkLogin = createAsyncThunk<LoginResponse, void, { rejectValue: any }>(
    'auth/checkLogin',
    async (_, { rejectWithValue }) => {
        try {
            const response = await apiClient.get('/check-login', { withCredentials: true });
            return response.data;
        } catch (error: any) {
            return rejectWithValue(error.response.data);
        }
    }
);

// ✅ 로그아웃 처리 (비동기)
export const logout = createAsyncThunk<void, void, { rejectValue: any }>(
    'auth/logout',
    async (_, { rejectWithValue }) => {
        try {
            await apiClient.post('/logout', {}, { withCredentials: true });
        } catch (error: any) {
            return rejectWithValue(error.response.data);
        }
    }
);

const authSlice = createSlice<AuthState, any>({
    name: 'auth',
    initialState,
    reducers: {  //reducer: 해당 상태를 변경하는 함수
        login:(state,action) =>{
            state.isAuthenticated =true;
            state.email =action.payload.email;
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(checkLogin.fulfilled, (state, action) => {
                state.isAuthenticated = true; //로그인 성공시 인증된 사용자
                state.email = action.payload.email; //상태로 갱신할 이메일 = 로그인에 사용된 이메일
            })
            .addCase(checkLogin.rejected, (state) => {
                state.isAuthenticated = false; //로그인 거부 된 사용자 = 로그인 안한상태
                state.email = null; //상태 email = 없음
            })
            .addCase(logout.fulfilled, (state) => {
                state.isAuthenticated = false;
                state.email = null;
            });
    },
});
export const {login} =authSlice.actions;
export default authSlice.reducer;
