FROM php:7.2-fpm


ARG USER_UID=1000
ARG USER_GID=1000

RUN getent group $USER_GID || groupadd -g $USER_GID docker
RUN useradd --shell /bin/bash -u $USER_UID -m -o -c "" -g $USER_GID admin

RUN apt-get update && apt-get install -y default-mysql-client --no-install-recommends \
 && rm /etc/apt/preferences.d/no-debian-php \
 && apt-get update -yqq \
 && apt-get install -y --force-yes --no-install-recommends \
         libz-dev \
         libpq-dev \
         libjpeg-dev \
         libpng-dev \
         libfreetype6-dev \
         libssl-dev \
         libmcrypt-dev \
         openssh-server \
         libmagickwand-dev \
         git \
         libxml2-dev \
         ghostscript


RUN docker-php-ext-install pdo_mysql soap

# Install for image manipulation
RUN docker-php-ext-install exif

# Install the PHP Imagick library
RUN pecl install imagick && \
    docker-php-ext-enable imagick

# Install the PHP gd library
RUN docker-php-ext-install gd && \
    docker-php-ext-configure gd \
        --enable-gd-native-ttf \
        --with-jpeg-dir=/usr/lib \
        --with-freetype-dir=/usr/include/freetype2 && \
    docker-php-ext-install gd

# Install the xdebug extension
RUN pecl install xdebug && docker-php-ext-enable xdebug
# Copy xdebug configration for remote debugging
COPY ./docker/php-fpm/xdebug.ini /usr/local/etc/php/conf.d

# PHP conf
COPY ./docker/php-fpm/php.ini /usr/local/etc/php/conf.d

# Install node and npm
RUN curl -sL https://deb.nodesource.com/setup_10.x | bash -
RUN apt-get install -y nodejs

