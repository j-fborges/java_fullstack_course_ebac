const gulp = require('gulp');
const sass = require('gulp-sass')(require('sass'));
const sourcemaps = require('gulp-sourcemaps');
const uglify = require('gulp-uglify');
const obfuscate = require('gulp-obfuscate');
const imagemin = require('gulp-imagemin');

function compressImgs() {
    return gulp.src('./source/images/*')
    .pipe(imagemin())
    .pipe(gulp.dest('./build/images'));
}

function minifyJs(){
    return gulp.src('./source/scripts/*.js')
    .pipe(uglify())
    .pipe(obfuscate())
    .pipe(gulp.dest('./build/scripts'));
}

function compileSass(){
    return gulp.src('./source/styles/main.scss')
    .pipe(sourcemaps.init())
    .pipe(sass({
        outputStyle: 'compressed'
    }))
    .pipe(sourcemaps.write('./maps'))
    .pipe(gulp.dest('./build/styles'))
}

exports.default = function(){
    gulp.watch('./sources/styles/*.scss', { ignoreInitial: false }, gulp.series(compileSass))
    gulp.watch('./sources/scripts/*.js', { ignoreInitial: false }, gulp.series(minifyJs))
    gulp.watch('./sources/images/*', { ignoreInitial: false }, gulp.series(compressImgs))
}

exports.minifyJs = minifyJs;
exports.compressImgs = compressImgs;