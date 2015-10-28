# GlideDemo

A demo describes how to load images by using Glide

一个展示了如何使用Glide加载图片的示例

###最简单的加载方式

```java
ImageView targetImageView = (ImageView) findViewById(R.id.imageView);
String internetUrl = "http://i.imgur.com/idojSYm.jpg";
Glide.with(context)
     .load(internetUrl)
     .into(targetImageView);
```

.with(Context context),此处的context就是你熟悉的context，没啥不一样的。就是有一点，里面可以是fragment。

.load(String imageUrl),你要加载的图片地址，当然也可以是uri或者file,亦或是R.drawable.xxxx。

.into(ImageView targetImageView)，你要加载的Imageview控件，当然也可以一个view。

###从不同的数据源加载图片

.load(String string) string可以为一个文件路径、uri或者url

.load(Uri uri) uri类型

.load(File file) 文件

.load(Integer resourceId) 资源Id,R.drawable.xxx或者R.mipmap.xxx

.load(byte[] model) byte[]类型

.load(T model) 自定义类型

