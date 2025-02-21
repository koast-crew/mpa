import * as React from 'react';

const App = React.lazy(() => import('@/App'));
const MainView = React.lazy(() => import('@/pages/MainView'));
const OlmapView = React.lazy(() => import('@/pages/OlmapView'));
const CesiumView = React.lazy(() => import('@/pages/CesiumView'));

export const routes = [
  {
    path: '/',
    Component: App,
    children: [
      {
        path: '/',
        Component: MainView,
      },
      {
        path: '/olmap',
        Component: OlmapView,
      },
      {
        path: '/cesium',
        Component: CesiumView,
      },
    ],
  },
];