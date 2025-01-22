import { createSlice } from '@reduxjs/toolkit';

interface NavigationState {
  tab: 'main';
}

const initialState: NavigationState = {
  tab: 'main',
};

const navigationSlice = createSlice({
  name: 'navigation',
  initialState,
  reducers: {
    setTab: (state, action) => {
      state.tab = action.payload;
    },
  },
});

export const { setTab } = navigationSlice.actions;
export default navigationSlice.reducer;
