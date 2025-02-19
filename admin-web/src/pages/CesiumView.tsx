import React from 'react';

import { CesiumMap, CesiumMapProps } from '@koast/ui';
import Cesium from 'cesium';

const CesiumView = () => {
  const [overlays, setOverlays] = React.useState<NonNullable<CesiumMapProps['overlays']>>([]);
  const [lon, setLon] = React.useState<number>(0);
  const [lat, setLat] = React.useState<number>(0);
  const handleMapOnClick = async(viewer: Cesium.Viewer, position: { lon: number, lat: number }) => {
    setLon(position.lon);
    setLat(position.lat);
    if (overlays.length > 0) {
      // handleOnCloseOverlay(overlays[0].id);
    }
    setOverlays((ol) => {
      return [...ol, {
        id: `overlay_${ (Number(ol.at(-1)?.id.replace('overlay_', '') ?? 0)) + 1 }`,
        position,
      }];
    });
  };
  const handleOnCloseOverlay = (id: string) => {
    setOverlays((ol) => {
      return ol.filter((item) => item.id !== id);
    });
  };
  return (
    <div className={'size-full'}>
      <CesiumMap
        onClick={handleMapOnClick}
        overlays={overlays}
        onCloseOverlay={handleOnCloseOverlay}
      >
        {lon}
        {lat}
        <button onClick={() => handleOnCloseOverlay(overlays[0].id)}>{'닫기'}</button>
      </CesiumMap>
    </div>
  );
};

export default CesiumView;
