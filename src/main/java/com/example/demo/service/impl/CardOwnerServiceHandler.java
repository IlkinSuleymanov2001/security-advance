package com.example.demo.service.impl;

import com.example.demo.aspect.performance.LogElapsedTime;
import com.example.demo.dao.entity.CardOwnerEntity;
import com.example.demo.dao.repository.CardOwnerRepository;
import com.example.demo.mapper.CardOwnerMapper;
import com.example.demo.model.request.card.CardOwnerRequest;
import com.example.demo.model.response.CardOwnerResponse;
import com.example.demo.service.impl.inter.CardOwnerService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.demo.exception.util.ExceptionUtil.EXCEPTION;
import static com.example.demo.model.enums.ErrorInfo.CARD_OWNER_NOT_FOUND;
import static lombok.AccessLevel.PRIVATE;


@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@LogElapsedTime
@Slf4j
public class CardOwnerServiceHandler implements CardOwnerService {


    CardOwnerRepository cardOwnerRepository;
    CardOwnerMapper cardOwnerMapper;

    public CardOwnerEntity createCardOwnerEntity(CardOwnerRequest cardOwnerRequest) {
        CardOwnerEntity cardOwnerEntity = cardOwnerMapper.toCardOwnerEntity(cardOwnerRequest);
        return cardOwnerRepository.save(cardOwnerEntity);
    }

    @Override
    public CardOwnerEntity getCardOwnerEntity(Long cardOwnerId) {
        return cardOwnerRepository.findById(cardOwnerId).orElseThrow(() ->
                EXCEPTION.notFoundThrowable(CARD_OWNER_NOT_FOUND));

    }


//    public void  test() {
//        CardOwnerEntity owner = cardOwnerRepository.findById(1L).get();
//        CardOwnerEntity owner1 = cardOwnerRepository.findById(1L).get();
//        owner.setAge(100);
//        owner1.setAge(50);
//        cardOwnerRepository.save(owner);
//       try {
//           cardOwnerRepository.save(owner1);
//       }catch (ObjectOptimisticLockingFailureException e) {
//           //e.printStackTrace();
//           log.error("lock works: {}",e.getMessage(),e);
//       }
//
//    }


    @Override
    public CardOwnerResponse getCardOwner(Long cardOwnerId) {
        CardOwnerEntity cardOwnerEntity = getCardOwnerEntity(cardOwnerId);
        return cardOwnerMapper.toCardOwnerResponse(cardOwnerEntity);

    }

}
