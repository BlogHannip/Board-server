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

const initialState: AuthState = {
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
    reducers: {
        login:(state,action) =>{
            state.isAuthenticated =true;
            state.email =action.payload.email;
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(checkLogin.fulfilled, (state, action) => {
                state.isAuthenticated = true;
                state.email = action.payload.email;
            })
            .addCase(checkLogin.rejected, (state) => {
                state.isAuthenticated = false;
                state.email = null;
            })
            .addCase(logout.fulfilled, (state) => {
                state.isAuthenticated = false;
                state.email = null;
            });
    },
});
export const {login} =authSlice.actions;
export default authSlice.reducer;
