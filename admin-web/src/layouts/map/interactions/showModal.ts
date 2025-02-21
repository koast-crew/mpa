import { Map } from 'ol';
import { transform } from 'ol/proj';

interface ShowModalProps {
  coordinate: number[];
  onShow: (lat: number, lon: number, coordinate: number[])=> void;
}

export const showModal = (_map: Map, { coordinate, onShow }: ShowModalProps) => {
  // EPSG:4326(위경도) 좌표로 변환 (표시용)
  const [lon, lat] = transform(coordinate, 'EPSG:3857', 'EPSG:4326');

  // 원본 좌표(EPSG:3857)를 모달 위치 지정에 사용
  onShow(lat, lon, coordinate);
};
