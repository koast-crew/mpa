import { useEffect, useRef } from 'react';
import { Overlay, Map } from 'ol';
import { transform } from 'ol/proj';

interface MapModalProps {
  isOpen: boolean;
  latitude: number;
  longitude: number;
  position: { x: number; y: number };
  map: Map; // OpenLayers Map 객체
}

export const MapModal = (props: MapModalProps) => {
  const {
    isOpen,
    latitude,
    longitude,
    map,
  } = props;
  const overlayRef = useRef<HTMLDivElement>(null);
  const overlayInstance = useRef<Overlay | null>(null);

  useEffect(() => {
    if (!isOpen || !overlayRef.current) return;

    const position = transform([longitude, latitude], 'EPSG:4326', 'EPSG:3857');
    overlayInstance.current = new Overlay({
      element: overlayRef.current,
      position: position,
      positioning: 'bottom-center',
      offset: [0, -20],
    });

    map.addOverlay(overlayInstance.current);

    return () => {
      if (overlayInstance.current) {
        map.removeOverlay(overlayInstance.current);
        overlayInstance.current = null;
      }
    };
  }, [isOpen, map, longitude, latitude]);

  if (!isOpen) return null;

  const convertDMS = (lon: number, lat: number) => {
    const toDegreesMinutesAndSeconds = (coordinate: number) => {
      const absolute = Math.abs(coordinate);
      const degrees = Math.floor(absolute);
      const minutesNotTruncated = (absolute - degrees) * 60;
      const minutes = Math.floor(minutesNotTruncated);
      const seconds = Math.floor((minutesNotTruncated - minutes) * 60);

      return degrees + '° ' + minutes + '′ ' + seconds + '″';
    };
    const latitude = toDegreesMinutesAndSeconds(lat);
    const latitudeCardinal = lat >= 0 ? 'N' : 'S';

    const longitude = toDegreesMinutesAndSeconds(lon);
    const longitudeCardinal = lon >= 0 ? 'E' : 'W';

    return { lon: longitude + ' ' + longitudeCardinal, lat: latitude + ' ' + latitudeCardinal };
  };

  return (
    <div ref={overlayRef}>
      <div className={'rounded-lg bg-white p-4 shadow-lg'}>
        <h3 className={'mb-2 text-lg font-bold'}>{'선택한 위치'}</h3>
        <p>{'위도: '}{convertDMS(longitude, latitude).lat}</p>
        <p>{'경도: '}{convertDMS(longitude, latitude).lon}</p>
      </div>
    </div>
  );
};
