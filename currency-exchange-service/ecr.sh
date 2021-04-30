aws ecr get-login-password --profile gnathaws+mb+admin | docker login --username AWS --password-stdin 205307382721.dkr.ecr.us-west-2.amazonaws.com
docker build -t currency-exchange-service .
docker tag currency-exchange-service:latest 205307382721.dkr.ecr.us-west-2.amazonaws.com/currency-exchange-service:latest
docker push 205307382721.dkr.ecr.us-west-2.amazonaws.com/currency-exchange-service:latest
