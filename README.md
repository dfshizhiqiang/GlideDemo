# GldieDemo

A demo describes how to load images by using Glide

一个展示了如何使用Glide加载图片的示例

1. [Hello,Glide]()

#####最简单的加载方式
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

2. [Load images from different data sources]()
