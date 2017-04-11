package com.bonuskids.moimaster.profile;

import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonuskids.moimaster.R;
import com.bonuskids.moimaster.data.Review;
import com.bonuskids.moimaster.data.Service;
import com.bonuskids.moimaster.databinding.ProfileFragBinding;
import com.bonuskids.moimaster.databinding.ReviewListItemBinding;
import com.bonuskids.moimaster.databinding.ServiceListItemBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private FirebaseRecyclerAdapter<Service, ServiceViewHolder> mAdapter;
    private FirebaseRecyclerAdapter<Review, ReviewViewHolder> mReviewAdapter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ProfileFragBinding binding = DataBindingUtil.inflate(inflater, R.layout.profile_frag, container, false);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String encodedEmail = firebaseUser.getEmail().replace(".", ",");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        Query services = reference.child("professionals").child(encodedEmail).child("services");
        Query reviews = reference.child("professionals").child(encodedEmail).child("reviews");

        Context context = binding.getRoot().getContext();
        binding.servicesList.setLayoutManager(new LinearLayoutManager(context));
        binding.servicesList.setNestedScrollingEnabled(false);

        mAdapter = new FirebaseRecyclerAdapter<Service, ServiceViewHolder>(
                Service.class,
                R.layout.service_list_item,
                ServiceViewHolder.class,
                services) {

            @Override
            protected void populateViewHolder(ServiceViewHolder viewHolder, Service model, int position) {
                viewHolder.bind(model);
            }

            @Override
            public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                ServiceListItemBinding binding = ServiceListItemBinding.inflate(layoutInflater, parent, false);
                return new ServiceViewHolder(binding);
            }
        };

        binding.servicesList.setAdapter(mAdapter);

        binding.reviewsList.setLayoutManager(new LinearLayoutManager(context));
        binding.reviewsList.setNestedScrollingEnabled(false);
        mReviewAdapter = new FirebaseRecyclerAdapter<Review, ReviewViewHolder>(
                Review.class,
                R.layout.review_list_item,
                ReviewViewHolder.class,
                reviews) {

            @Override
            protected void populateViewHolder(ReviewViewHolder viewHolder, Review model, int position) {
                viewHolder.bind(model);
            }

            @Override
            public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                ReviewListItemBinding binding = ReviewListItemBinding.inflate(layoutInflater, parent, false);
                return new ReviewViewHolder(binding);
            }
        };

        binding.reviewsList.setAdapter(mReviewAdapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null && mReviewAdapter != null) {
            mAdapter.cleanup();
            mReviewAdapter.cleanup();
        }
    }

    private static class ServiceViewHolder extends RecyclerView.ViewHolder {
        private final ServiceListItemBinding binding;

        public ServiceViewHolder(ServiceListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Service service) {
            binding.setService(service);
            binding.executePendingBindings();
        }
    }

    private static class ReviewViewHolder extends RecyclerView.ViewHolder {
        private final ReviewListItemBinding binding;

        public ReviewViewHolder(ReviewListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Review review) {
            binding.setReview(review);
            binding.executePendingBindings();
        }
    }
}
