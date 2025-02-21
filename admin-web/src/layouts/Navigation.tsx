import { LucideIcon } from 'lucide-react';
import { SquareUserRound, Map, Rotate3D } from 'lucide-react';
import { Link, useMatch } from 'react-router-dom';

// 네비게이션 아이템 타입 정의
interface NavItem {
  path: string;
  icon: LucideIcon;
  label: string;
};

function Navigation() {
  // 네비게이션 아이템 설정
  const navItems: NavItem[] = [
    { path: '/', icon: SquareUserRound, label: '관리자' },
    { path: '/olmap', icon: Map, label: 'olmap' },
    { path: '/cesium', icon: Rotate3D, label: 'cesium' },
  ];

  const matchHome = useMatch('/');
  const matchOlmap = useMatch('/olmap');
  const matchCesium = useMatch('/cesium');
  const matches = [matchHome, matchOlmap, matchCesium];

  return (
    <div className={'box-border flex size-full flex-col border-t border-gray7 bg-main'}>
      {navItems.map(({ path, icon: Icon, label }, index) => (
        <Link
          key={path}
          to={path}
          className={
            'box-border flex h-[65px] w-full flex-col items-center justify-center border-b border-gray7'
            + (matches[index] ? ' bg-blue text-light' : '')
          }
        >
          <Icon className={'text-light'} width={'20'} height={'20'} />
          <div className={'m-[2px] text-[12px] font-bold text-light'}>{label}</div>
        </Link>
      ))}
    </div>
  );
}

export default Navigation;
