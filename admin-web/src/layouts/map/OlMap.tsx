import React from 'react';
import Map from 'ol/Map';
import View from 'ol/View';
import { XYZ } from 'ol/source';
import TileLayer from 'ol/layer/Tile';
import { transform } from 'ol/proj';
import { clickPoint, showModal } from './interactions';
import { MapModal } from './mapComponents';

function OlMap() {
  const mapRef = React.useRef<Map | null>(null);
  const [modalOpen, setModalOpen] = React.useState(false);
  const [coordinates, setCoordinates] = React.useState<{ lat: number; lon: number }>({ lat: 0, lon: 0 });
  const [modalPosition, setModalPosition] = React.useState<{ x: number; y: number }>({ x: 0, y: 0 });

  React.useEffect(() => {
    mapRef.current = new Map({
      target: 'olContainer',
      layers: [
        new TileLayer({
          source: new XYZ({
            url: 'https://flight.amo.go.kr/osm_tiles2/{z}/{x}/{y}.png',
          }),
        }),
      ],
      view: new View({
        projection: 'EPSG:3857',
        center: transform([127.77, 35.5], 'EPSG:4326', 'EPSG:3857'),
        zoom: 7,
        minZoom: 2,
        maxZoom: 14,
      }),
      controls: [],
    });

    if (!mapRef.current) return;

    // 클릭 인터랙션 설정
    const clickInteraction = clickPoint({
      map: mapRef.current,
      color: 'red',
      onPointClick: (coordinate: number[]) => {
        setModalOpen(false);

        showModal(mapRef.current!, {
          coordinate,
          onShow: (lat, lon, coordinate) => {
            setCoordinates({ lat, lon });
            setModalPosition({ x: coordinate[0], y: coordinate[1] });
            setModalOpen(true);
          },
        });
      },
    });

    // 레이아웃이 모두 반영된 후에 OpenLayers에 사이즈 업데이트를 요청
    setTimeout(() => {
      mapRef.current?.updateSize();
    }, 0);

    // 창 크기 변경 시에도 업데이트
    const handleResize = () => {
      mapRef.current?.updateSize();
    };
    window.addEventListener('resize', handleResize);

    return () => {
      clickInteraction();
      window.removeEventListener('resize', handleResize);
      mapRef.current?.setTarget('');
      mapRef.current = null;
    };
  }, []);

  return (
    <div
      id={'olContainer'}
      className={'size-full'}
    >
      {mapRef.current && (
        <MapModal
          map={mapRef.current}
          isOpen={modalOpen}
          latitude={coordinates.lat}
          longitude={coordinates.lon}
          position={modalPosition}
        />
      )}
    </div>
  );
};

export default OlMap;