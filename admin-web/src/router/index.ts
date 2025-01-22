import * as React from 'react';

const App = React.lazy(() => import('@/App'));
const MainView = React.lazy(() => import('@/pages/MainView'));
const SecondView = React.lazy(() => import('@/pages/SecondView'));

export const routes = [
  {
    path: '/',
    element: React.createElement(App),
    children: [
      {
        path: '/',
        element: React.createElement(MainView),
      },
      {
        path: '/nav2',
        element: React.createElement(SecondView),
      },
    ],
  },
];