import {configureStore, createSlice} from "@reduxjs/toolkit"

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

export const{updateEmail,updateNickName} =userSlice.actions;

export default store;