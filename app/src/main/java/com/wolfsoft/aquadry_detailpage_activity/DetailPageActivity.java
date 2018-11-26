package com.wolfsoft.aquadry_detailpage_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import model.Categories;
import model.Products;


public class DetailPageActivity extends AppCompatActivity {

    private LinearLayout linear_progressbar;

    private Toolbar toolbar;
    private TextView toolBarTxt,btn;

    private RecyclerView recyclerView;
    private RecycleAdapter_AddProduct mAdapter;
    private int status_code;
    private String token,totalPriceOfProducts;


//    private ProductArrayList productsArrayList;

    private TextView quantityOfTotalProduct,priceOfTotalProduct,next;
    private Categories categories;

    private int[] IMAGES = {R.drawable.shoes,R.drawable.shoes};
    private String[] NamES = {"Shoes","T-shirt"};
    private String[] PRICE = {"150","132"};

    private FrameLayout frameLoading;
    private ImageView loadingImage;

    Animation startAnimation,zoomOut, bounceAnimation;

    Runnable rr;
    Handler handler = new Handler();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpage);

        categories = new Categories();
        categories.productsArrayList = new ArrayList<>();


        for (int i=0; i<NamES.length ; i++){
            Products products = new Products();
            products.setName(NamES[i]);
            products.setPrice(PRICE[i]);
            products.setImage(IMAGES[i]);

            categories.productsArrayList.add(products);

        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        btn= (TextView)findViewById(R.id.btn);
        frameLoading= (FrameLayout) findViewById(R.id.frameLoading);
        loadingImage= (ImageView) findViewById(R.id.loadingImage);

        startAnimation = AnimationUtils.loadAnimation(DetailPageActivity.this, R.anim.cycle_7);
        bounceAnimation = AnimationUtils.loadAnimation(DetailPageActivity.this, R.anim.bounce);
        zoomOut= AnimationUtils.loadAnimation(DetailPageActivity.this, R.anim.zoom_out);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                btn.startAnimation(zoomOut);


                frameLoading.setVisibility(View.VISIBLE);
                loadingImage.startAnimation(startAnimation);
                Intent it = new Intent(DetailPageActivity.this, MainActivity.class);
                startActivity(it);
            }
        });


//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ProductArrayList productsForSend = new ProductArrayList();
//                productsForSend =  productsToBeSend(categoriesAndProducts);
//                PrefUtils.setProducts(productsForSend, getActivity());
//
//                if (productsForSend.getProductsArrayList().size() >0){
//                    Intent it = new Intent(getActivity(), ProductSummaryActivity.class);
//                    startActivity(it);
//                }else {
//                    Toast.makeText(getActivity(), "Please Select The Products", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });




        mAdapter = new RecycleAdapter_AddProduct(DetailPageActivity.this,categories);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(DetailPageActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }


    public class RecycleAdapter_AddProduct extends RecyclerView.Adapter<RecycleAdapter_AddProduct.MyViewHolder> {

        Context context;
        boolean showingFirst = true;
        private Categories categories;



        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView image;
            TextView title;
            TextView price;
            TextView quantityTxt;
            private LinearLayout llMinus,llPlus;
            int quantity;


            public MyViewHolder(View view) {
                super(view);

                image = (ImageView) view.findViewById(R.id.image);
                title = (TextView) view.findViewById(R.id.title);
                price = (TextView) view.findViewById(R.id.price);
                quantityTxt = (TextView) view.findViewById(R.id.quantityTxt);
                llPlus = (LinearLayout)view.findViewById(R.id.llPlus);
                llMinus = (LinearLayout)view.findViewById(R.id.llMinus);

            }

        }



        public RecycleAdapter_AddProduct(Context context, Categories categories) {
            this.categories = categories;
            this.context = context;
        }

        @Override
        public RecycleAdapter_AddProduct.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_add_product, parent, false);


            return new RecycleAdapter_AddProduct.MyViewHolder(itemView);


        }


        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(final RecycleAdapter_AddProduct.MyViewHolder holder, final int position) {
//            Products movie = productsList.get(position);

            holder.title.setText(categories.getProductsArrayList().get(position).getName());
            holder.price.setText(categories.getProductsArrayList().get(position).getPrice());
            holder.quantityTxt.setText(categories.getProductsArrayList().get(position).getQuantity() + "");


            holder.quantity = categories.getProductsArrayList().get(position).getQuantity();
            int totalPrice = holder.quantity * Integer.parseInt(categories.getProductsArrayList().get(position).getPrice());


            if (categories.getProductsArrayList().get(position).getQuantity() > 0){
                holder.quantityTxt.setVisibility(View.VISIBLE);
                holder.llMinus.setVisibility(View.VISIBLE);
            }else {
                holder.quantityTxt.setVisibility(View.GONE);
                holder.llMinus.setVisibility(View.GONE);
            }


            categories.getProductsArrayList().get(position).setPriceAsPerQuantity(""+ totalPrice);


            holder.llPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (holder.quantity <10){

                        holder.quantity = holder.quantity + 1;
                        categories.getProductsArrayList().get(position).setQuantity(holder.quantity);
                        categories.getProductsArrayList().get(position).setPriceAsPerQuantity(""+holder.quantity * Integer.parseInt(categories.getProductsArrayList().get(position).getPrice()));

                        holder.quantityTxt.setText("" + holder.quantity);
                    }


                    notifyDataSetChanged();

                }
            });


            holder.llMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (holder.quantity > 0 && holder.quantity <= 10){

                        holder.quantity = holder.quantity - 1;
                        categories.getProductsArrayList().get(position).setQuantity(holder.quantity);
                        categories.getProductsArrayList().get(position).setPriceAsPerQuantity(""+holder.quantity * Integer.parseInt(categories.getProductsArrayList().get(position).getPrice()));

                        holder.quantityTxt.setText("" + holder.quantity);


                    }

                    notifyDataSetChanged();

                }
            });



        }

        @Override
        public int getItemCount() {
            return categories.getProductsArrayList().size();
        }


    }
}
