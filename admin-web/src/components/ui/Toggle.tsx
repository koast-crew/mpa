interface ToggleProps {
  checked?: boolean;
  onChange?: (checked: boolean)=> void;
  disabled?: boolean;
  className?: string;
  color?: 'blue' | 'orange';
  size?: 'sm' | 'md' | 'lg';
}

export default function Toggle(props: ToggleProps) {
  const {
    checked = false,
    onChange,
    disabled = false,
    className = '',
    color = 'blue',
    size = 'sm',
  } = props;

  const colorStyle = {
    blue: 'peer-checked:bg-blue',
    orange: 'peer-checked:bg-orange',
  };

  const sizeStyle = {
    sm: 'h-4 w-7 after:size-3',
    md: 'h-5 w-9 after:size-4',
    lg: 'h-6 w-11 after:size-5',
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    onChange?.(e.target.checked);
  };

  return (
    <label className={'relative inline-flex cursor-pointer items-center'}>
      <input
        type={'checkbox'}
        checked={checked}
        onChange={handleChange}
        disabled={disabled}
        className={'peer sr-only'}
      />
      <div className={`
        rounded-full bg-gray4
        after:absolute after:start-[2px] after:top-[2px]
        after:rounded-full after:bg-white
        after:transition-all peer-checked:after:translate-x-full
        ${ colorStyle[color] }
        ${ sizeStyle[size] }
        ${ disabled ? 'cursor-not-allowed opacity-50' : '' }
        ${ className }
      `}
      />
    </label>
  );
}