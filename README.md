# adapter-delegates
Multi type for RecyclerView

### Dependency
Step 1. Add the JitPack repository to your build file
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Step 2. Add the dependency
```
dependencies {
    compile 'com.github.fbsum:adapter-delegates:1.0.0'
}
```

## Usage

下面是一个只有一种 view type 的 Adapter 的完整代码，其中 ContentItem 为 JavaBean 类

PS：代码看起来很多，但大部分模版代码可以由 File Template 和 ViewHolder 生成插件自动生成

```java
/**
 * Adapter 类
 */
class ContentItemAdapter extends DelegationAdapter<ContentItem>
        implements SimpleAdapterDelegate.OnViewClickListener {

    ContentItemAdapter(Activity activity) {
        // 添加 Delegates
        ContentItemDelegate delegate = new ContentItemDelegate(activity);
        // 为 Delegate 添加布局点击事件
        delegate.setOnViewClickListener(this);
        this.addDelegate(delegate);
    }

    /**
     * 点击事件的统一回调函数
     *
     * @param view     被点击的 View（itemView 或者 itemView's 子 View）
     * @param position item 的 position
     */
    @Override
    public void onClick(View view, int position) {
        if (position < 0 || position >= getItemCount()) {
            return;
        }
        switch (view.getId()) {

        }
    }

    /**
     * Delegate 类（一个布局 type 对应创建一个 Delegate 类）
     * PS：Delegate 类尽量只做界面绑定逻辑，事件处理逻辑应回调到 Adapter 处理
     */
    private static class ContentItemDelegate extends SimpleAdapterDelegate<ContentItem> {

        ContentItemDelegate(Activity activity) {
            // view type 对应的布局 id
            super(activity, R.layout.layout_content);
        }

        @Override
        public boolean isForViewType(@NonNull ContentItem item) {
            // 判断当前 item 是否对应当前 Delegate
            return true;
        }

        /**
         * 创建 ViewHolder
         */
        @NonNull
        @Override
        protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull View itemView) {
            final ViewHolder viewHolder = new ViewHolder(itemView);
            
            /* 设置点击事件（其它事件处理类似）：
             * 1.在 onCreateViewHolder() 设置，并且共用同一个 onClickListener 实例，能减少开销;
             * 2.将点击事件统一传给 onViewClickListener，统一回调到 Adapter 处理，分离职责
             */
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onViewClickListener != null) {
                        int position = viewHolder.getAdapterPosition();
                        onViewClickListener.onClick(v, position);
                    }
                }
            };
            viewHolder.contentTextView.setOnClickListener(onClickListener);

            return viewHolder;
        }

        @Override
        protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ContentItem item, @NonNull List<Object> list) {
            ViewHolder vh = (ViewHolder) viewHolder;
            vh.contentTextView.setText(item.content);
        }

        /**
         * Delegate 对应的 ViewHolder
         */
        private static class ViewHolder extends RecyclerView.ViewHolder {
            private TextView contentTextView;

            ViewHolder(View itemView) {
                super(itemView);
                contentTextView = (TextView) itemView.findViewById(R.id.tv_main_item_content);
            }
        }
    }
}
```

## Thanks

https://github.com/sockeqwe/AdapterDelegates/