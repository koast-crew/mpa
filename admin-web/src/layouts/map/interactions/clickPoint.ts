import { Map } from 'ol';
import VectorLayer from 'ol/layer/Vector';
import VectorSource from 'ol/source/Vector';
import { Feature } from 'ol';
import Point from 'ol/geom/Point';
import { Style, Circle, Fill } from 'ol/style';

interface ClickPointProps {
  map: Map
  color?: string
  radius?: number
  reset?: boolean
  onPointClick?: (coordinate: number[])=> void
}

export const clickPoint = (props: ClickPointProps) => {
  const {
    map,
    color = 'red',
    radius = 6,
    reset = true,
    onPointClick,
  } = props;

  // 벡터 레이어와 소스 생성
  const vectorSource = new VectorSource();
  const vectorLayer = new VectorLayer({
    source: vectorSource,
    style: new Style({
      image: new Circle({
        radius: radius,
        fill: new Fill({ color: color }),
      }),
    }),
  });

  // 맵에 레이어 추가
  map.addLayer(vectorLayer);

  // 클릭 이벤트 리스너 설정
  map.on('click', (event) => {
    const clickedCoordinate = event.coordinate;

    // 이미 존재하는 feature 찾기
    const existingFeature = vectorSource.getFeatures().find((feature) => {
      const point = feature.getGeometry() as Point;
      const coord = point.getCoordinates();
      const tolerance = 0.0001; // 오차 허용 범위
      return Math.abs(coord[0] - clickedCoordinate[0]) < tolerance
             && Math.abs(coord[1] - clickedCoordinate[1]) < tolerance;
    });

    if (existingFeature) {
      // 이미 존재하는 포인트를 재클릭한 경우 삭제
      vectorSource.removeFeature(existingFeature);
      return;
    }

    // reset이 true인 경우 이전 포인트들을 모두 삭제
    if (reset) {
      vectorSource.clear();
    }

    // 새로운 포인트 추가
    const feature = new Feature({
      geometry: new Point(clickedCoordinate),
    });

    vectorSource.addFeature(feature);

    // 클릭 이벤트 콜백 호출
    if (onPointClick) {
      onPointClick(clickedCoordinate);
    }
  });

  // 정리 함수 반환
  return () => {
    map.removeLayer(vectorLayer);
  };
};