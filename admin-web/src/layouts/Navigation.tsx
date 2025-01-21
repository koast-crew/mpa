import { FishSymbol, Map } from 'lucide-react';
import { Link, useLocation } from 'react-router-dom';

function Navigation() {
  const { pathname } = useLocation();

  return (
    <div className={'box-border flex size-full flex-col border-t border-zinc-500 bg-slate-800'}>
      <Link to={'/'} className={'box-border flex h-[65px] w-full flex-col items-center justify-center border-b border-zinc-500' + (pathname === '/' ? ' bg-blue text-light' : '')}>
        <FishSymbol className={'text-light'} width={'20'} height={'20'} />
        <div className={'m-[2px] text-[12px] font-bold text-light'}>{'nav1'}</div>
      </Link>
      <Link to={'/nav2'} className={'box-border flex h-[65px] w-full flex-col items-center justify-center border-b border-zinc-500' + (pathname === '/nav2' ? ' bg-blue text-light' : '')}>
        <Map className={'text-light'} width={'20'} height={'20'} />
        <div className={'m-[2px] text-[12px] font-bold text-light'}>{'nav2'}</div>
      </Link>
    </div>
  );
}

export default Navigation;
